import React from 'react';
import {Link} from 'react-router-dom';
import './Nav.css';

function Nav() {
    return (
        <nav>
            <ul>
                <li><Link to="/movies">Movies</Link></li>
                <li><Link to="/movies/create">Add Movie</Link></li>
            </ul>
        </nav>
    );
}

export default Nav;
