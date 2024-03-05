import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { addReviewToMovie } from '../services/api';
import './AddReviewForm.css'

const AddReviewForm = () => {
    const { id } = useParams();
    const [reviewText, setReviewText] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await addReviewToMovie(id, { reviewText });
            console.log('Review added successfully!');
            setReviewText('');
        } catch (error) {
            setError(error);
        }
        setLoading(false);
    };

    return (
        <div className="add-review-form-container">
            <h2 className="form-title">Add Review</h2>
            <form className="review-form" onSubmit={handleSubmit}>
                <textarea
                    className="review-textarea"
                    value={reviewText}
                    onChange={(e) => setReviewText(e.target.value)}
                    placeholder="Enter your review"
                    rows={4}
                    cols={50}
                    required
                />
                <button className="submit-btn" type="submit" disabled={loading}>Submit Review</button>
                {error && <div className="error-message">Error: {error.message}</div>}
            </form>
        </div>
    );
};

export default AddReviewForm;
