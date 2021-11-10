import { eLearningConstans } from '../js/elearningConstants.js';

export function getThemes(spaceName, isRoot, keyword, offset, limit) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/theme/findThemes?spaceName=${spaceName}&isRoot=${isRoot}&q=${keyword}&offset=${offset}&limit=${limit}`, {
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

export function getThemeById(themeId) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/theme/findThemeById/${themeId}`, {
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

export function getChildThemes(spaceName, parentThemeId, keyword, offset, limit) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/theme/getChildThemes?spaceName=${spaceName}&parentThemeId=${parentThemeId}&q=${keyword}&offset=${offset}&limit=${limit}`, {
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
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/theme/deleteTheme/${deleteId}`, {
    method: 'DELETE',
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function createTheme(theme) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/theme/addTheme`, {
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
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/theme/updateTheme`, {
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

export function updateThemeColor(theme, color) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/theme/updateThemeColor/${theme.id}?color=${color}`, {
    method: 'PUT',
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    } else {
      return response.json();
    }
  });
}