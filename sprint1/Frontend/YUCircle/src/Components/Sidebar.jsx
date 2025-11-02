import { Link } from "react-router-dom"
import { useState } from "react"

export default function Sidebar() {
    const [isOpen, setIsOpen] = useState(false)
    return <>
 {/* Hamburger button - only visible on small/medium screens */}
        <button 
            onClick={() => setIsOpen(!isOpen)}
            className="xl:hidden fixed top-4 left-4 z-50 p-2 text-white rounded bg-(--yorku-red)"
        >
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                {isOpen ? (
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                ) : (
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
                )}
            </svg>
        </button>

        <div className={`${isOpen ? 'translate-x-0' : '-translate-x-full'} xl:translate-x-0 fixed xl:static h-screen w-64 xl:w-1/7 text-white transition-transform duration-300 ease-in-out z-40 bg-(--yorku-red)`}>        
        <div className="flex flex-col">
                <Link to="/" className="py-10 border-b border-white pb-2 pl-4 pb-10">Welcome, Bob123</Link>
                <Link to="/register" className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer">Register</Link>
                <p className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer">Login</p>
                <p className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer">Resource Board</p>
                <p className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer">Find Friends</p>
                <p className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer">Upload Schedule</p>
				<Link to="/make-post" className="pb-2 pl-4 pt-2 border-b border-white hover:bg-(--yorku-blue) transition-colors duration-300 cursor-pointer"> Make a Post</Link>

        </div>
    </div>
     {isOpen && (
            <div 
                className="fixed inset-0 bg-black z-30 xl:hidden"
                onClick={() => setIsOpen(false)}
            />
        )}
    </>
}