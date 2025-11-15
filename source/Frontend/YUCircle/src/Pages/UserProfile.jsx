import { useState, useEffect } from "react";
import useFetch from "../Hooks/useFetch";

export default function UserProfile() {

  const { get, post } = useFetch("http://localhost:8080/api/students/by-username/");

  const [student, setStudent] = useState(null);
  //const [loading, setLoading] = useState(true);
  const [editingBio, setEditingBio] = useState(false);
  const [bio, setBio] = useState("");
  const [savingBio, setSavingBio] = useState(false);
  const [bioMessage, setBioMessage] = useState(null);

  useEffect(() => {
    const username = localStorage.getItem("username");
    get(username).then(data => {
      setStudent(data);
    })
  }, []);


  //if (loading) return <p className="text-red-500 p-4">Loading...</p>;
  if (!student) return <p className="text-white p-4">No student data</p>;

  function handleSaveBio() {
    setSavingBio(true);
    setBioMessage(null);

    // Update locally
    setStudent(prev => ({ ...prev, bio }));
    setBioMessage("Bio saved locally.");
    setEditingBio(false);
    setSavingBio(false);
    setTimeout(() => setBioMessage(null), 3000);
  }
  

  return (
    <div className="min-h-screen flex gap-6 p-8 text-white">
      <aside className="max-w-sm w-full rounded shadow-md" style={{ backgroundColor: "var(--yorku-red, #B31B1B)" }}>
        <div className="p-6">
          <h2 className="text-2xl font-bold mb-2">Your Profile</h2>
          <p className="text-sm opacity-90 mb-4">
            Welcome back, <span className="font-semibold">{student.firstName + " " + student.lastName}</span>
          </p>

          <div className="space-y-2 mb-4">
            <div>
              <div className="text-xs text-white/80">First name</div>
              <div className="font-medium">{student.firstName}</div>
            </div>
            <div>
              <div className="text-xs text-white/80">Last name</div>
              <div className="font-medium">{student.lastName}</div>
            </div>
            <div>
              <div className="text-xs text-white/80">Email</div>
              <div className="font-medium">{student.email}</div>
            </div>
            <div>
              <div className="text-xs text-white/80">Major</div>
              <div className="font-medium">{student.major === null ? "We don't know your major yet." : student.major}</div>
            </div>
          </div>

          <div className="mt-4">
            <div className="flex items-center justify-between mb-2">
              <div className="text-xs text-white/80">Bio</div>
              <div>
                {!editingBio ? (
                  <button onClick={() => setEditingBio(true)} className="text-sm underline underline-offset-2">Edit</button>
                ) : (
                  <button onClick={() => { setEditingBio(false); setBio(student.bio ?? ""); }} className="text-sm underline underline-offset-2">Cancel</button>
                )}
              </div>
            </div>

            {!editingBio ? (
              <div className="text-sm text-white/90">
                {student.bio || <span className="text-white/60">No bio yet. Click Edit to add one.</span>}
              </div>
            ) : (
              <div className="space-y-2">
                <textarea
                  value={bio}
                  onChange={(e) => setBio(e.target.value)}
                  rows={4}
                  className="w-full p-2 rounded text-black"
                  placeholder="Tell people about yourself..."
                />
                <div className="flex items-center gap-2">
                  <button onClick={handleSaveBio} disabled={savingBio} className="bg-white text-black px-3 py-1 rounded">
                    {savingBio ? "Saving..." : "Save"}
                  </button>
                  {bioMessage && <div className="text-sm text-white/80 ml-3">{bioMessage}</div>}
                </div>
              </div>
            )}
          </div>
        </div>
      </aside>

      <main className="flex-1">
        <h1 className="text-3xl font-bold text-black mb-6">Posts</h1>
        <section className="bg-white/5 rounded-lg p-6 shadow-sm min-h-[200px]">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-xl font-semibold text-white">Posts</h2>
            <div className="text-sm text-white/60">No posts loaded</div>
          </div>
          <div className="text-black/70">No posts yet. Can be connected with a post feature.</div>
        </section>
      </main>
    </div>
  );
}

