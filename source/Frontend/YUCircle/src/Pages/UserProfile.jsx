import { useState, useEffect } from "react";
import useFetch from "../Hooks/useFetch";

export default function UserProfile() {
  const { get, patch } = useFetch("http://localhost:8080/api/students/");
  const username = localStorage.getItem("username");

  const [student, setStudent] = useState(null);

  const [editingField, setEditingField] = useState(null);
  const [fieldValues, setFieldValues] = useState({
    firstName: "",
    lastName: "",
    major: "",
    bio: "",
  });

  const [passwordValues, setPasswordValues] = useState({
    currentPassword: "",
    newPassword: "",
    confirmPassword: "",
  });
  const [resettingPassword, setResettingPassword] = useState(false);
  const [passwordMessage, setPasswordMessage] = useState(null);
  

  const [saving, setSaving] = useState(false);
  const [message, setMessage] = useState(null);

  // Load the student
  useEffect(() => {
    if (!username) return;
    get(`by-username/${username}`).then((data) => {
      setStudent(data);

      setFieldValues({
        firstName: data.firstName || "",
        lastName: data.lastName || "",
        major: data.major || "",
        bio: data.bio || "",
      });
    });
  }, []);

  if (!student)
    return <p className="text-white p-4">No student data</p>;

  // Save a single field
  async function handleSaveField(field) {
    if (!field) return;

    setSaving(true);
    setMessage(null);

    try {
      const updated = await patch(`update/${username}`, {
        [field]: fieldValues[field],
      });

      setStudent(updated);
      setEditingField(null);
      setMessage("Saved!");

      setTimeout(() => setMessage(null), 2000);
    } catch (err) {
      setMessage("Error saving.");
    }

    setSaving(false);
  }

  async function handleResetPassword() {
    const { currentPassword, newPassword, confirmPassword } = passwordValues;
  
    if (!currentPassword || !newPassword || !confirmPassword) {
      setPasswordMessage("Please fill all fields.");
      return;
    }
  
    if (newPassword !== confirmPassword) {
      setPasswordMessage("New passwords do not match.");
      return;
    }
  
    setResettingPassword(true);
    setPasswordMessage(null);
  
    try {
      await patch(`update/${username}`, {
        currentPassword,
        newPassword,
      });
      setPasswordMessage("Password updated successfully!");
      setPasswordValues({ currentPassword: "", newPassword: "", confirmPassword: "" });
    } catch (err) {
      setPasswordMessage("Error updating password.");
    }
  
    setResettingPassword(false);
  }
  

  // Generic editable field renderer
  function renderEditableField(label, field) {
    const isEditing = editingField === field;

    return (
      <div className="mb-4">
        <div className="flex items-center justify-between mb-1">
          <div className="text-xs text-white/80">{label}</div>

          {!isEditing ? (
            <button
              onClick={() => setEditingField(field)}
              className="text-sm underline underline-offset-2"
            >
              Edit
            </button>
          ) : (
            <button
              onClick={() => {
                setEditingField(null);
                setFieldValues((prev) => ({
                  ...prev,
                  [field]: student[field] || "",
                }));
              }}
              className="text-sm underline underline-offset-2"
            >
              Cancel
            </button>
          )}
        </div>

        {!isEditing ? (
          <div className="font-medium text-sm text-white/90">
            {student[field] || (
              <span className="text-white/60">Not set</span>
            )}
          </div>
        ) : (
          <div className="space-y-2">
            <input
              type="text"
              value={fieldValues[field]}
              onChange={(e) =>
                setFieldValues((prev) => ({
                  ...prev,
                  [field]: e.target.value,
                }))
              }
              className="w-full p-2 rounded text-black"
            />

            <button
              onClick={() => handleSaveField(field)}
              disabled={saving}
              className="bg-white text-black px-3 py-1 rounded"
            >
              {saving ? "Saving..." : "Save"}
            </button>
          </div>
        )}
      </div>
    );
  }

  return (
    <div className="min-h-screen flex gap-6 p-8 text-white">
      {/* Sidebar */}
      <aside
        className="max-w-sm w-full rounded shadow-md"
        style={{ backgroundColor: "var(--yorku-red, #B31B1B)" }}
      >
        <div className="p-6">

          <h2 className="text-2xl font-bold mb-2">Your Profile</h2>
          <p className="text-sm opacity-90 mb-4">
            Welcome back,
            <span className="font-semibold">
              {" " + student.firstName + " " + student.lastName}
            </span>
          </p>

          {/* FIRST NAME */}
          {renderEditableField("First name", "firstName")}

          {/* LAST NAME */}
          {renderEditableField("Last name", "lastName")}

          {/* USERNAME (READ ONLY) */}
          <div className="mb-4">
            <div className="text-xs text-white/80">Username</div>
            <div className="font-medium text-sm text-white/90 mt-1">
              {student.username}
            </div>
          </div>

          {/* EMAIL (READ ONLY) */}
          <div className="mb-4">
            <div className="text-xs text-white/80">Email</div>
            <div className="font-medium text-sm text-white/90 mt-1">
              {student.email}
            </div>
          </div>

          {/* MAJOR */}
          {renderEditableField("Major", "major")}

          {/* BIO — SPECIAL (textarea) */}
          <div className="mt-4">
            <div className="flex items-center justify-between mb-2">
              <div className="text-xs text-white/80">Bio</div>

              {!editingField ? (
                <button
                  onClick={() => setEditingField("bio")}
                  className="text-sm underline underline-offset-2"
                >
                  Edit
                </button>
              ) : editingField === "bio" ? (
                <button
                  onClick={() => {
                    setEditingField(null);
                    setFieldValues((prev) => ({
                      ...prev,
                      bio: student.bio || "",
                    }));
                  }}
                  className="text-sm underline underline-offset-2"
                >
                  Cancel
                </button>
              ) : null}
            </div>

            {editingField !== "bio" ? (
              <div className="text-sm text-white/90">
                {student.bio || (
                  <span className="text-white/60">
                    No bio yet. Click Edit to add one.
                  </span>
                )}
              </div>
            ) : (
              <div className="space-y-2">
                <textarea
                  value={fieldValues.bio}
                  onChange={(e) =>
                    setFieldValues((prev) => ({
                      ...prev,
                      bio: e.target.value,
                    }))
                  }
                  rows={4}
                  className="w-full p-2 rounded text-black"
                  placeholder="Tell people about yourself..."
                />

                <button
                  onClick={() => handleSaveField("bio")}
                  disabled={saving}
                  className="bg-white text-black px-3 py-1 rounded"
                >
                  {saving ? "Saving..." : "Save"}
                </button>
              </div>
            )}
          </div>

          {message && (
            <p className="text-xs text-white/80 mt-3">{message}</p>
          )}
        </div>
        <div className="mt-6">
        <h3 className="text-sm font-semibold mb-2 text-white/90">Reset Password</h3>
          <input
            type="password"
            placeholder="Current Password"
            value={passwordValues.currentPassword}
            onChange={(e) =>
            setPasswordValues((prev) => ({ ...prev, currentPassword: e.target.value }))
          }
          className="w-full p-2 rounded mb-2 text-black"
          />

          <input
            type="password"
            placeholder="New Password"
            value={passwordValues.newPassword}
            onChange={(e) =>
            setPasswordValues((prev) => ({ ...prev, newPassword: e.target.value }))
          }
          className="w-full p-2 rounded mb-2 text-black"
            />

  <input
    type="password"
    placeholder="Confirm New Password"
    value={passwordValues.confirmPassword}
    onChange={(e) =>
      setPasswordValues((prev) => ({ ...prev, confirmPassword: e.target.value }))
    }
    className="w-full p-2 rounded mb-2 text-black"
  />

  <button
    onClick={handleResetPassword}
    disabled={resettingPassword}
    className="bg-white text-black px-3 py-1 rounded"
  >
    {resettingPassword ? "Updating..." : "Reset Password"}
  </button>

  {passwordMessage && (
    <p className="text-xs text-white/80 mt-2">{passwordMessage}</p>
  )}
</div>

      </aside>

      {/* MAIN */}
      <main className="flex-1">
        <h1 className="text-3xl font-bold text-black mb-6">Posts</h1>
        <section className="bg-white/5 rounded-lg p-6 shadow-sm min-h-[200px]">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-xl font-semibold text-white">Posts</h2>
            <div className="text-sm text-white/60">No posts loaded</div>
          </div>
          <div className="text-black/70">
            No posts yet. Can be connected with a post feature.
          </div>
        </section>
      </main>
    </div>
  );
}
