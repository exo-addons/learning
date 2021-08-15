export function getTutos() {
  return fetch('/portal/rest/tuto/getAllTutos', {
    method: 'GET'
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
  return fetch(`/portal/rest/tuto/getTutoById/${tutoId}`, {
    method: 'GET'
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
  return fetch(`/portal/rest/tuto/deleteTuto/${deleteId}`, {
    method: 'DELETE'
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function tutoPost(tuto) {
  return fetch('/portal/rest/tuto/addTuto', {
    method: 'POST',
    body: JSON.stringify(tuto),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function tutoUpdate(tuto) {
  return fetch('/portal/rest/tuto/updateTuto', {
    method: 'PUT',
    body: JSON.stringify(tuto),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function getAllTutosByTheme(themeId) {
  return fetch(`/portal/rest/tuto/getAllTutosByTheme/${themeId}`, {
    method: 'GET'
  })
    .then((response) => {
      if (!response || !response.ok) {
        throw new Error('Response code indicates a server error', response);
      } else {
        return response.json();
      }
    });
}

export function getTutosByName(id,tutoTitle) {
  return fetch(`/portal/rest/tuto/getTutosByName/${id}/${tutoTitle}`, {
    method: 'GET'
  })
    .then((response) => {
      if (!response || !response.ok) {
        throw new Error('Response code indicates a server error', response);
      } else {
        return response.json();
      }
    });
}