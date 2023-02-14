import React, { useEffect, useState } from 'react';
import axios from 'axios';

function FriendList() {
    const [friends, setFriends] = useState([]);
    const [userId, setUserId] = useState('');

    useEffect(() => {
        if (userId) {
            axios.get(`/api/friends/${userId}`)
                .then(response => {
                    setFriends(response.data);
                })
                .catch(error => {
                    alert('친구 목록을 불러오는 중에 에러가 발생하였습니다.');
                });
        }
    }, [userId]);

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post(`/api/friends/${userId}/add-friend/${e.target.friendId.value}`)
            .then(response => {
                alert('친구 추가가 완료되었습니다.');
            })
            .catch(error => {
                alert('친구 추가에 실패하였습니다.');
            });
    };

    return (
        <div>
            <label>
                User ID:
                <input type="text" value={userId} onChange={e => setUserId(e.target.value)} />
            </label>
            <ul>
                {friends.map(friend => (
                    <li key={friend.id}>{friend.name}</li>
                ))}
            </ul>
            <form onSubmit={handleSubmit}>
                <label>
                    친구 ID:
                    <input type="text" name="friendId" />
                </label>
                <button type="submit">
                    친구 추가
                </button>
            </form>
        </div>
    );
}

export default FriendList;
