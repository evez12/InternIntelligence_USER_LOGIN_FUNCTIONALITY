import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Login from './login'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <h1 >Login Page</h1>
      <Login />
    </div>
  )
}

export default App
