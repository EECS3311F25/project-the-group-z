package main;

@Service
public class StudentCommandService {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentCommandService(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Student registerStudent(StudentRequest request) {

        Student student = new Student();
        student.setUsername(request.getUsername());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());

        return studentRepo.save(student);
    }

    // Process uploaded images and save schedule in database
    public void processScheduleImage(Long studentNumber, MultipartFile imageFile) {
        try {
            // Convert MultipartFile to a temporary File object
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + imageFile.getOriginalFilename());
            imageFile.transferTo(convFile);

            // Initialise Tesseract OCR engine
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");  // Path to trained data files
            tesseract.setLanguage("eng");  // Assume language is English
            tesseract.setPageSegMode(6); // Assume a uniform block layout
            String result = tesseract.doOCR(convFile);  // Extract text from image

            // Parse extracted text into Course entities
            List<Course> courses = parseSchedule(result);
            // Identify student by studentId (passed in request)
            Student student = studentRepo.findById(studentNumber).orElseThrow(() -> new RuntimeException("Student not found"));

            // Save each course to the database and link it to the student
            for (Course course : courses) {
                course.setStudent(student);
                courseRepo.save(course);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to process image", e);
        }
    }

    // Parse OCR text into a list of Course objects
    private List<Course> parseSchedule(String text) {
        List<Course> courses = new ArrayList<>();
        String[] blocks = text.split("\n\n"); // Split by empty lines between blocks

        for (String block : blocks) {
            if (block.contains("Lecture") || block.contains("Tutorial") || block.contains("Laboratory")) {
                Course course = new Course();

                // Extract course code and name
                Matcher codeMatcher = Pattern.compile("[A-Z]{2,} \\w+ \\d\\.0").matcher(block);
                if (codeMatcher.find()) course.setName(codeMatcher.group());

                // Extract type
                if (block.contains("Lecture")) course.setType("Lecture");
                else if (block.contains("Tutorial")) course.setType("Tutorial");
                else if (block.contains("Laboratory")) course.setType("Lab");

                // Extract time
                Matcher timeMatcher = Pattern.compile("\\d{1,2}:\\d{2}–\\d{1,2}:\\d{2}").matcher(block);
                if (timeMatcher.find()) course.setTime(timeMatcher.group());

                // Extract day
                for (String day : List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")) {
                    if (block.contains(day)) {
                        course.setDay(day);
                        break;
                    }
                }

                // Extract room
                Matcher roomMatcher = Pattern.compile("room\\s+([A-Z]+\\s*\\d+)", Pattern.CASE_INSENSITIVE).matcher(block);
                if (roomMatcher.find()) course.setRoom(roomMatcher.group(1));

                courses.add(course);
            }
        }
        return courses;
    }

    //TESTING
    public void testOcrWithSampleImage() {
        try {
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
            tesseract.setLanguage("eng");
            tesseract.setPageSegMode(6);

            File image = new File("C:/Users/Alice/Downloads/schedule.png"); // Replaced with your image path
            String result = tesseract.doOCR(image);

            System.out.println("OCR Result:\n" + result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}