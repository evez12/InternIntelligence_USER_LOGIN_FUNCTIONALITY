import React, { useState } from 'react';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [jwt, setJwt] = useState("");
    const [profile, setProfile] = useState(null);
    const [isRegistering, setIsRegistering] = useState(false);

    const handleOAuthLogin = (provider) => {
        window.location.href = `http://localhost:8080/oauth2/authorize/${provider}?redirect_uri=http://localhost:5174`;
    };

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

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/signup", {
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
            }
            else if(response.status === 400) {
                setMessage("Username already exists. Please try again.");
            }
            else {
                setMessage("Registration failed. Please try again.");
            }
        } catch (error) {
            console.log(error);
            setMessage("Please try again. Registration");
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

    const handleLogout = () => {
        setUsername("");
        setPassword("");
        setProfile(null);
        setJwt("");
        setMessage("Logout successful");
    };

    return (
        <div style={{ maxWidth: "400px", margin: "0 auto", padding: "20px" }}>
            {!profile && !isRegistering ? (
                <form onSubmit={handleLogin} style={{ display: "flex", flexDirection: "column", gap: "15px" }}>
                    <div>
                        <label>Username</label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                            style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                        />
                    </div>
                    <div>
                        <label>Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                        />
                    </div>
                    <div style={{ display: "flex", justifyContent: "space-between" }}>
                        <button type="submit" style={{ padding: "10px 20px" }}>Login</button>
                        <button type="button" onClick={() => setIsRegistering(true)} style={{ padding: "10px 20px" }}>
                            Register
                        </button>
                    </div>
                    <hr style={{ margin: "15px 0" }} />
                    <button
                        type="button"
                        onClick={() => handleOAuthLogin("google")}
                        style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: "10px", padding: "10px 20px" }}
                    >
                        <img src="https://pngdow.com/files/preview/800x800/11722280805b4vi91domiophd87cjmnmwtjhyopqholatbnxkuahnxchtd6y9zusubuq8mgxlhi6kg2nmduczbhghtlyfrdsx0sitjn3ngget0q.png" alt="Google" width="20" />
                        Login with Google
                    </button>
                    <button
                        type="button"
                        onClick={() => handleOAuthLogin("github")}
                        style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: "10px", padding: "10px 20px" }}
                    >
                        <img src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" alt="GitHub" width="20" />
                        Login with GitHub
                    </button>
                </form>
            ) : !profile && isRegistering ? (
                <form onSubmit={handleRegister} style={{ display: "flex", flexDirection: "column", gap: "15px" }}>
                    <div>
                        <label>Username</label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                            style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                        />
                    </div>
                    <div>
                        <label>Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                        />
                    </div>
                    <button type="submit" style={{ padding: "10px 20px" }}>Register</button>
                    <button
                        type="button"
                        onClick={() => setIsRegistering(false)}
                        style={{ padding: "10px 20px" }}
                    >
                        Back to Login
                    </button>
                </form>
            ) : (
                <div className="profile">
                    <h3>User Profile</h3>
                    <p>Username: {profile.results.username}</p>
                    <p>Roles: {profile.results.roles.join(", ")}</p>
                    <p>JWT Token: {jwt}</p>
                    <button onClick={handleLogout} style={{ padding: "10px 20px" }}>Logout</button>
                </div>
            )}
            <div className="message">{message && <p>{message}</p>}</div>
        </div>
    );
};

export default Login;
