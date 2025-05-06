import React, { use, useState } from 'react';
import './signup.css';

function SignupPage() {
  const [name, setName] = useState(null);
  const[email,setEmail]=useState(null);
  const [contact, setContact] = useState(null);
  const[licenceNumber,setLicenceNumber]=useState(null);
  const[address,setAddress]=useState(null);
  const [username, setUsername] = useState(null);
  const [password, setPassword] = useState(null);

  const signUp = async ($e) => {
    $e.preventDefault();

    try {
        const response = await axios.post('http://localhost:8081/api/customer/add',
            {
                "name": name,
                "email":email,
                "contact": contact,
                "licenceNumber":licenceNumber,
                "address":address,
                "user": {
                    "username": username,
                    "password": password
                }
            }
        )

        console.log('sign Up success....')
    }
    catch (err) {
        console.log(err)
    }
}
  return (
    <div className="registration-wrapper d-flex justify-content-center align-items-center vh-100">
      <div className="card p-5 registration-card w-75">
        <h3 className="mb-4 text-white">Sign Up</h3>

        <h5 className="text-white mb-4">Personal Information</h5>
        <form className="row g-3" onSubmit={($e)=>signUp($e)}>
        <div className="row mb-3">
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Full Name" 
            onChange={($event) => { setName($event.target.value) }} />
          </div>
          <div className="col-md-6 mb-3">
            <input type="email" className="form-control" placeholder="Email Address" 
            onChange={($event) => { setEmail($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Phone Number" 
            onChange={($event) => { setContact($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Driver's License Number" 
            onChange={($event) => { setLicenceNumber($event.target.value) }}/>
          </div>
          <div className="col-12 mb-4">
            <textarea className="form-control" rows="2" placeholder="Address"
            onChange={($event) => { setAddress($event.target.value) }}></textarea>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="User Name" 
            onChange={($event) => { setUsername($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="password" className="form-control" placeholder="Password" 
            onChange={($event) => { setPassword($event.target.value) }}/>
          </div>
        </div>

        <h5 className="text-white mb-3">Identity Verification</h5>
        <div className="row mb-4">
          <div className="col-md-6 mb-3">
            <div className="upload-box text-center">
              <i className="bi bi-cloud-arrow-up fs-2"></i>
              <p className="mb-0">Upload a file</p>
            </div>
          </div>
          <div className="col-md-6 mb-3">
            <div className="upload-box text-center">
              <i className="bi bi-person-circle fs-2"></i>
              <p className="mb-0">Upload a photo</p>
            </div>
          </div>
        </div>

        <div className="d-flex justify-content-end">
          <button className="btn btn-light me-3">Cancel</button>
          <button className="btn btn-success">Signup</button>
        </div>
        </form>
      </div>
    </div>
  );
}

export default SignupPage;
