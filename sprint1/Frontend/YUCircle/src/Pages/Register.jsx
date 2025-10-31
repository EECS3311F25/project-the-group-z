export default function Register() {
    return <>
    <div className="min-h-screen flex justify-center items-center text-white">
        <div className="bg-(--yorku-red) max-w-md w-full p-10 pb-20 pt-15">
                <h1 className="text-center">YUCircle</h1>
                <div className="flex flex-col gap-5 mt-2">
                    <h2 className="text-center font-bold">Register</h2>
                        <p className="text-xs">Sign up for an account, verify using your York University email.</p>
                        <div className="flex flex-col mb-6">
                        <label className="mb-1">Username/Email</label>
                        <input 
                            type="text" 
                            className="border-b-2 border-gray-400 outline-none p-1" style={{'--tw-border-opacity': 1, borderBottomColor: 'var(--yorku-blue)'}}
                        />
                        </div>

                        <div className="flex flex-col mb-6">
                        <label className="mb-1">Password</label>
                        <input 
                            type="password" 
                            className="border-b-2 border-gray-400 outline-none p-1" style={{'--tw-border-opacity': 1, borderBottomColor: 'var(--yorku-blue)'}}

                        />
                        </div>
                </div>
                <div className="flex justify-center">
                    <button className="bg-white text-black px-4 py-2 rounded">Verify Email</button>
                </div>
        </div>
    </div>
    </>
}