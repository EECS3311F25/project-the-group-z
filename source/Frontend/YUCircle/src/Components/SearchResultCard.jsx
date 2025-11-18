import "../UserSearch.css";

function SearchResultCard({ student }) {
  function sendFriendReq() {}

  return (
    <div className="result">
      <div className="result-left-side">
        <div className="result-student-info">
          <p>
            <b>Name:</b>
            {student.firstName}
            {student.lastName}
          </p>
          <p>
            <b>Major:</b>
            {student.major}
          </p>
          <p>
            <b>Year:</b>
            {student.year}
          </p>
        </div>
      </div>
      <div className="result-right-side">
        <button>View Profile</button>
        <button className="result-button">
          <img src="../Assets/add-friend-button.png" onClick={sendFriendReq} />
        </button>
      </div>
    </div>
  );
}

export default SearchResultCard;
