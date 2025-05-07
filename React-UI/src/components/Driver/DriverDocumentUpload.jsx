import axios from "axios";
import { useState } from "react";
import { useParams } from "react-router";

function DriverDocumentUpload() {
  const [licenceDoc, setLicenceDoc] = useState(null);
  const [aadhaarDoc, setAadhaarDoc] = useState(null);
  const [addressProofDoc, setAddressProofDoc] = useState(null);
  const [msg, setMsg] = useState("");


  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      
      let docData = {
        "licenceDoc": licenceDoc,"aadhaarDoc": aadhaarDoc,"addressProofDoc":addressProofDoc
      }

      await axios.post(`http://localhost:8080/api/driver-documents/driver/${driverId}`, docData, {
        headers: {
            Authorization:`Bearer ${localStorage.getItem('token')}`,
          "Content-Type": "multipart/form-data",
        },
      });

      setMsg("Driver registered and documents uploaded successfully.");
    } catch (error) {
      console.error(error);
      setMsg("Something went wrong!");
    }
  };

  return (
    <div>
      <h2>Document Upload</h2>
      <form onSubmit={handleSubmit}>
        
        <label>Driving Licence Document:</label>
        <input type="file"  onChange={(e) => setLicenceDoc(e.target.files[0])} />

        <label>Aadhaar Card:</label>
        <input type="file"  onChange={(e) => setAadhaarDoc(e.target.files[0])} />

        <label>Address Proof:</label>
        <input type="file"  onChange={(e) => setAddressProofDoc(e.target.files[0])} />

        <button type="submit">Submit</button>
      </form>
      {msg && <p>{msg}</p>}
    </div>
  );
}

export default DriverDocumentUpload;
