import React, { useState } from 'react';
import './LoginPage.css'; 
import { Link } from 'react-router';

function LoginPage() {
  const [username,setUsername]=useState(null)
  const [password,setPassword]=useState(null)
  const [msgUsername,setMsgUsername]=useState(null)
  const [msgPassword,setMsgPassword]=useState(null)
  const login = ()=>{
    let isCorrect=false;
    if(username==null  || username ==="" || username==undefined){
        setMsgUsername("Username cannot be blank!!")
        return            
    }
    else{
        setMsgUsername(null)
    }
    if(password==null || password=="" || password==undefined){
        setMsgPassword("Password cannot be blank!!!")
        return
    }
    else{
        setMsgPassword(null)
    }

    let body = {
        username:username,
        password:password
    }


    axios.post("http://localhost:8080/api/userLogin/token/generate",body).then(response => {
        let token = response.data.token
        localStorage.setItem('token', token)
        localStorage.setItem('username', username)

        axios.get("http://localhost:8080/api/userLogin/userDetails",
        {
            headers:{
                Authorization:`Bearer ${token}`
            }
        }
    )
    .then(resp => {
        switch (resp.data.role) {
            case 'MANAGER':
                //navigate to customer dashboard
                navigate("/driverlistformanager")
                break;
            case 'USER_DEFAULT':
                //navigate to vendor dashboard
                navigate("/driverlist")
                break;
            case 'Driver':
              navigate("/driverdashboard")
              break;
            default:
                break;
        }
    })
    })

  }
  return (
    <div className="login-wrapper d-flex justify-content-center align-items-center vh-100">
      <div className="login-card text-center p-4">
        <h2 className="mb-2 text-white">Login</h2>
        <p className="text-muted mb-4">Please enter your name and password.</p>
        <div className="card-body text-white small">
                        {
                            msgUsername==null?"":<div className="mb-4">
                                {msgUsername}
                            </div>
                        }
                        {
                            msgPassword==null?"":<div className="mb-4">
                                {msgPassword}
                            </div>
                        }

        <div className="text-start mb-3">
          <label className="form-label text-success small">Enter Your Username</label>
          <input type="email" className="form-control custom-input" placeholder="Username" 
          onChange= {($event)=>{setUsername($event.target.value);setMsgUsername(null)}}/>
        </div>

        <div className="text-start mb-4">
          <label className="form-label text-success small">Enter your password</label>
          <input type="password" className="form-control custom-input" placeholder="*******" 
          onChange= {($event)=>{setPassword($event.target.value);setMsgPassword(null)}}/>
        </div>

        <button className="btn btn-success w-100 mb-3" onClick={()=>{login()}}>Login</button>
        </div>

        <div className="mb-3">
          <a href="#" className="small text-success text-decoration-none">Forget password</a>
        </div>

        <div className="d-flex align-items-center mb-3">
          <hr className="flex-grow-1 text-secondary" />
          <span className="mx-2 text-secondary small">or</span>
          <hr className="flex-grow-1 text-secondary" />
        </div>
        <Link to="/becomedriver" className="btn btn-outline-success w-100">Sign up</Link>
      </div>
    </div>
  );
}

export default LoginPage;
