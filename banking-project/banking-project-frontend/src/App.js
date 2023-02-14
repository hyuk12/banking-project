import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('/users', { username, password })
            .then(response => {
                alert('회원가입이 완료되었습니다.');
            })
            .catch(error => {
                alert('회원가입에 실패하였습니다.');
            })
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Username:
                <input type="text" name="username" value={username} onChange={e => setUsername(e.target.value)} />
            </label>
            <label>
                Password:
                <input type="password" name="password" value={password} onChange={e => setPassword(e.target.value)} />
            </label>
            <button type="submit">Register</button>
        </form>
    );
}


export default App;


