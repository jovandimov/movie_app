import React, {useState} from 'react';
import {addMovie} from '../services/api';
import './AddMovieForm.css';
const AddMovieForm = () => {
    const [formData, setFormData] = useState({
        title: '',
        description: '',
        genre: '',
        year: ''
    });
    const [error, setError] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await addMovie(formData);
            console.log('Movie added successfully!');
        } catch (error) {
            setError(error.message);
        }
    };

    return (
        <div className="add-movie-form-container">
            <h2 className="form-title">Add Movie</h2>
            {error && <div className="error-message">Error: {error}</div>}
            <form className="movie-form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="title">Title:</label>
                    <input type="text" id="title" name="title" value={formData.title} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="description">Description:</label>
                    <input type="text" id="description" name="description" value={formData.description} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="genre">Genre:</label>
                    <input type="text" id="genre" name="genre" value={formData.genre} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="year">Year:</label>
                    <input type="number" id="year" name="year" value={formData.year} onChange={handleChange} />
                </div>
                <button className="submit-btn" type="submit">Add Movie</button>
            </form>
        </div>
    );
};

export default AddMovieForm;
