@Service
public class StudentCommandService {
    private final StudentRepo studentRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentCommandService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
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
}