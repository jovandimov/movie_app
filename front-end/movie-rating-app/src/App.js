import React from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import MoviesPage from "./pages/MoviesPage";
import MovieDetails from './components/MovieDetails';
import AddMovieForm from './components/AddMovieForm';
import AddReviewForm from './components/AddReviewForm';
import Nav from "./components/Nav";

function App() {
    return (
        <Router>
            <div>
                <Nav/>
                <Routes>
                    <Route path="/" element={<MoviesPage/>}/>
                    <Route path="/movies" element={<MoviesPage/>}/>
                    <Route path="/movies/:id" element={<MovieDetails/>}/>
                    <Route path="/movies/create" element={<AddMovieForm/>}/>
                    <Route path="/movies/:id/reviews/create" element={<AddReviewForm/>}/>
                </Routes>
            </div>
        </Router>
    );
}

export default App;
