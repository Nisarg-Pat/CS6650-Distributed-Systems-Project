import NavBar from "../NavBar";
import React, {useState} from "react";


const SearchScreen = () => {

    const [search, setSearch] = useState('');

    return (
        <>
            <NavBar/>
            <input value={search} onChange={(e) => setSearch(e.target.value)}/>
        </>

    );
}

export default SearchScreen;