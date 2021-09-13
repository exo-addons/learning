export function getThemes(spaceName, keyword, offset, limit) {
  return fetch(`/portal/rest/theme/findThemes?spaceName=${spaceName}&q=${keyword}&offset=${offset}&limit=${limit}`, {
    method: 'GET',
    credentials: 'include',
  }).then((response) => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    } else {
      return response.json();
    }
  });
}

export function deleteTheme(deleteId) {
  return fetch(`/portal/rest/theme/deleteTheme/${deleteId}`, {
    method: 'DELETE',
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function createTheme(theme) {
  return fetch('/portal/rest/theme/addTheme', {
    method: 'POST',
    credentials: 'include',
    body: JSON.stringify(theme),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    } else {
      return response.json();
    }
  });
}

export function updateTheme(theme) {
  return fetch('/portal/rest/theme/updateTheme', {
    method: 'PUT',
    credentials: 'include',
    body: JSON.stringify(theme),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    } else {
      return response.json();
    }
  });
}