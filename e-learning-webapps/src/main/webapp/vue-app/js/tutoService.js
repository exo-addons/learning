export function getTutos() {
  return fetch('/portal/rest/tutorial/getAllTutos', {
    method: 'GET',
    credentials: 'include',
  })
    .then((response) => {
      if (!response || !response.ok) {
        throw new Error('Response code indicates a server error', response);
      } else {
        return response.json();
      }
    });
}

export function getTutoById(tutoId) {
  return fetch(`/portal/rest/tutorial/getTutoById/${tutoId}`, {
    method: 'GET',
    credentials: 'include',
  })
    .then((response) => {
      if (!response || !response.ok) {
        throw new Error('Response code indicates a server error', response);
      } else {
        return response.json();
      }
    });
}

export function deleteTuto(deleteId) {
  return fetch(`/portal/rest/tutorial/deleteTuto/${deleteId}`, {
    method: 'DELETE',
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function tutoPost(tuto) {
  return fetch('/portal/rest/tutorial/addTuto', {
    method: 'POST',
    body: JSON.stringify(tuto),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function tutoUpdate(tuto) {
  return fetch('/portal/rest/tutorial/updateTuto', {
    method: 'PUT',
    body: JSON.stringify(tuto),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function getTutorialsByTheme(themeId, offset, limit) {
  return fetch(`/portal/rest/tutorial/getTutorialsByTheme/${themeId}?offset=${offset}&limit=${limit}`, {
    method: 'GET',
    credentials: 'include',
  })
    .then((response) => {
      if (!response || !response.ok) {
        throw new Error('Response code indicates a server error', response);
      } else {
        return response.json();
      }
    });
}

export function getTutosByName(id, tutoTitle) {
  return fetch(`/portal/rest/tutorial/getTutosByName/${id}/${tutoTitle}`, {
    method: 'GET',
    credentials: 'include',
  })
    .then((response) => {
      if (!response || !response.ok) {
        throw new Error('Response code indicates a server error', response);
      } else {
        return response.json();
      }
    });
}