import React, {useState, useEffect} from 'react';
import {Link, useParams} from 'react-router-dom';
import {getMovieById} from '../services/api';
import './MovieDetails.css';

const MovieDetails = () => {
    const {id} = useParams();
    const [movieData, setMovieData] = useState(null);


    useEffect(() => {
        const fetchMovieData = async () => {
            try {
                const response = await getMovieById(id);
                setMovieData(response);
            } catch (error) {
                console.error('Error fetching movie data:', error);
            }
        };

        fetchMovieData().then(r => r);
    }, [id]);


    if (!movieData) {
        return <div>Loading...</div>;
    }

    return (
        <div className="movie-details-container">
            <h2>Movie Details</h2>
            <p>Title: {movieData.movie.title}</p>
            <p>Description: {movieData.movie.description}</p>
            <p>Genre: {movieData.movie.genre}</p>
            <p>Year: {movieData.movie.year}</p>
            <Link to={`/movies/${id}/reviews/create`}>Add Review</Link>
        </div>
    );
};

export default MovieDetails;
