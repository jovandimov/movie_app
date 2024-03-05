import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import {getMovies, getMoviesWithFilters} from '../services/api';
import './MovieList.css';

function MoviesList() {
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [filters, setFilters] = useState({
        title: '',
        genre: '',
        description: '',
        year: '',
    });

    let timer;

    useEffect(() => {
        const getMoviesData = async () => {
            try {
                setLoading(true);
                const response = await getMoviesWithFilters(filters);
                setMovies(response.content);
                setLoading(false);
            } catch (error) {
                setError(error);
                setLoading(false);
            }
        };

        clearTimeout(timer);

        timer = setTimeout(() => {
            getMoviesData().then(movie => movie);
        }, 500);

        return () => {
            clearTimeout(timer)
        };

    }, [filters]);

    const handleFilterChange = (event) => {
        const {name, value} = event.target;
        setFilters({
            ...filters,
            [name]: value,
        });
    }

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <div className="movies-container">
            <h2>Movies</h2>
            <div>
                <input
                    type="text"
                    placeholder="Search by title"
                    name="title"
                    value={filters.title}
                    onChange={handleFilterChange}
                />
                <input
                    type="text"
                    placeholder="Filter by genre"
                    name="genre"
                    value={filters.genre}
                    onChange={handleFilterChange}
                />
                <input
                    type="text"
                    placeholder="Filter by description"
                    name="description"
                    value={filters.description}
                    onChange={handleFilterChange}
                />
                <input
                    type="number"
                    placeholder="Filter by year"
                    name="year"
                    value={filters.year}
                    onChange={handleFilterChange}
                />
            </div>
            <ul>
                {movies.map((movie) => (
                    <li key={movie.movie.id}>
                        <h3>
                            <Link to={`/movies/${movie.movie.id}`}>
                                {movie.movie.title}
                            </Link>
                        </h3>
                        <p>ID: {movie.movie.id}</p>
                        <p>Description: {movie.movie.description}</p>
                        <p>Genre: {movie.movie.genre}</p>
                        <p>Year: {movie.movie.year}</p>
                        <p>Average Rating: {movie.averageRating}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default MoviesList;
