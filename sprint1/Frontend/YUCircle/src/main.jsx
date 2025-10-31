import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Sidebar from './Components/Sidebar'
import Register from "./pages/Register"
import Login from "./pages/Login"
import { BrowserRouter, Routes, Route } from "react-router-dom"



createRoot(document.getElementById('root')).render(
  <StrictMode>
      <BrowserRouter>
      <div className="flex">
        <Sidebar />
        <div className="flex-1">
          <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  </StrictMode>,
)
