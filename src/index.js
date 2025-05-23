const API_URL = '/api/todos';

async function fetchTodos() {
    const res = await fetch(API_URL);
    return res.json();
}

async function addTodo(title) {
    const res = await fetch(API_URL, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({title, completed: false})
    });
    return res.json();
}

async function updateTodo(id, todo) {
    const res = await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(todo)
    });
    return res.json();
}

async function deleteTodo(id) {
    await fetch(`${API_URL}/${id}`, {method: 'DELETE'});
}

function renderTodos(todos) {
    const list = document.getElementById('todo-list');
    list.innerHTML = '';
    todos.forEach(todo => {
        const li = document.createElement('li');
        li.className = todo.completed ? 'completed' : '';
        li.innerHTML = `
            <input type="checkbox" ${todo.completed ? 'checked' : ''} data-id="${todo.id}" />
            <span>${todo.title}</span>
            <button data-id="${todo.id}" class="delete">Delete</button>
        `;
        list.appendChild(li);
    });
}

async function refreshTodos() {
    const todos = await fetchTodos();
    renderTodos(todos);
}

document.getElementById('todo-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const input = document.getElementById('todo-input');
    if (input.value.trim()) {
        await addTodo(input.value.trim());
        input.value = '';
        refreshTodos();
    }
});

document.getElementById('todo-list').addEventListener('change', async (e) => {
    if (e.target.type === 'checkbox') {
        const id = e.target.getAttribute('data-id');
        const todos = await fetchTodos();
        const todo = todos.find(t => t.id == id);
        if (todo) {
            todo.completed = e.target.checked;
            await updateTodo(id, todo);
            refreshTodos();
        }
    }
});

document.getElementById('todo-list').addEventListener('click', async (e) => {
    if (e.target.classList.contains('delete')) {
        const id = e.target.getAttribute('data-id');
        await deleteTodo(id);
        refreshTodos();
    }
});

refreshTodos();