import { Link } from "react-router-dom"
export default function Sidebar() {
    return <>
    <div className="bg-(--yorku-red) h-screen w-1/8 text-white">
        <div className="flex flex-col">
                <Link to="/" className="py-10 border-b border-white pb-2 pl-4 pb-10">Welcome, Bob123</Link>
                <Link to="/register" className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer">Register</Link>
                <p className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer">Login</p>

        </div>
    </div>
    </>
}