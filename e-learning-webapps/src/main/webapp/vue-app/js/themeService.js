export function getThemes() {
  return fetch('/portal/rest/theme/getAllThemes', {
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

export function getThemeById(themeId) {
  return fetch(`/portal/rest/theme/getThemeById/${themeId}`, {
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

export function deleteTheme(deleteId) {
  return fetch(`/portal/rest/theme/deleteTheme/${deleteId}`, {
    method: 'DELETE'
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function themePost(theme) {
  return fetch('/portal/rest/theme/addTheme', {
    method: 'POST',
    body: JSON.stringify(theme),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function themeUpdate(theme) {
  return fetch('/portal/rest/theme/updateTheme', {
    method: 'PUT',
    body: JSON.stringify(theme),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function getThemeNames() {
  return fetch('portal/rest/theme/getAllThemeNames', {
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

export function getThemesByName(themeName) {
  return fetch(`portal/rest/theme/findAllThemesByName/${themeName}`, {
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