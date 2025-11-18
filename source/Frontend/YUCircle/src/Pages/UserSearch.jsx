import SearchResultCard from "../Components/SearchResultCard";
import "../UserSearch.css";
import { useState } from "react";

function UserSearch() {
  const [searchQuery, setSearchQuery] = useState("");
  const [foundStudents, setfoundStudents] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!searchQuery.trim()) return;

    if (loading) return;
    setLoading(true);

    try {
      postMessage(`/by-username/${searchQuery}`, searchQuery).then((data) => {
        try {
          const results = [];
          data.map((e) => {
            const temp = {
              firstName: e.firstName,
              lastName: e.lastName,
            };
            results.push(temp);
          });
          setfoundStudents((prevStudents) => [...prevStudents, results]);
        } catch {
          setfoundStudents([]);
        }
      });
    } catch (err) {
      console.log(err);
      setError("Failed to search students...");
    } finally {
      setLoading(false);
    }

    setSearchQuery("");
  };

  return (
    <div className="user-search-body">
      <h1>Find Your New Friends</h1>
      <h2>See who has similar schedules to your!</h2>

      <form onSubmit={handleSearch} className="search-form">
        <input
          type="text"
          placeholder="Search by Name, Course, Major, Year"
          className="search-input"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        ></input>
        <input type="submit" className="submit-search"></input>
      </form>

      {error && <div className="error-message">{error}</div>}

      {loading ? (
        <div className="loading">Loading...</div>
      ) : (
        <div className="results-box">
          {foundStudents.map((student) => (
            <SearchResultCard student={student} key={student.id} />
          ))}
        </div>
      )}
    </div>
  );
}

export default UserSearch;
