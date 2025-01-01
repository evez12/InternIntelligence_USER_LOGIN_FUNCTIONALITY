import React, { useState } from 'react';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [jwt, setJwt] = useState("");
    const [profile, setProfile] = useState(null);
    const [isRegistering, setIsRegistering] = useState(false);

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/signin", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username, password }),
            });
            if (response.ok) {
                const data = await response.json();
                setJwt(data.results.jwtToken);
                setMessage("Login successful");
                fetchUserProfile(data.results.jwtToken);
            } else {
                setMessage("Login failed. Please check your credentials.");
            }
        } catch (error) {
            console.log(error);
            setMessage("Please try again. Login");
        }
    };

    const fetchUserProfile = async (token) => {
        try {
            const response = await fetch("http://localhost:8080/profile", {
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${token}`,
                }
            });
            if (response.ok) {
                const data = await response.json();
                setProfile(data);
            } else {
                setMessage("Profile fetch failed. Please check your credentials.");
            }
        } catch (error) {
            console.log(error);
            setMessage("Please try again. Profile");
        }
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username, password }),
            });
            if (response.ok) {
                const data = await response.json();
                setJwt(data.results.jwtToken); 
                setMessage("Registration successful");
                fetchUserProfile(data.results.jwtToken); 
            } else {
                setMessage("Registration failed. Please try again.");
            }
        } catch (error) {
            console.log(error);
            setMessage("Please try again. Registration");
        }
    };

    const handleLogout = () => {
        setUsername("");
        setPassword("");
        setProfile(null);
        setJwt("");
        setMessage("Logout successful");
    };

    return (
        <div>
            {!profile && !isRegistering ? (
                <form onSubmit={handleLogin}>
                    <div>
                        <label>Username  </label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label>Password  </label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">Login</button>
                    <button type="button" onClick={() => setIsRegistering(true)}>Register</button>
                </form>
            ) : !profile && isRegistering ? (
                <form onSubmit={handleRegister}>
                    <div>
                        <label>Username  </label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label>Password  </label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">Register</button>
                    <button type="button" onClick={() => setIsRegistering(false)}>Back to Login</button>
                </form>
            ) : (
                <div className='profile'>
                    <h3>User Profile</h3>   
                    <p>Username: {profile.results.username}</p>
                    <p>Roles: {profile.results.roles.join(", ")}</p>
                    <p>JWT Token: {jwt}</p>
                    <p>Message: {profile.status}</p>
                    <button onClick={handleLogout}>Logout</button>
                </div>
            )}
            <div className='message'>{message && <p>{message}</p>}</div>
        </div>
    );
};

export default Login;
