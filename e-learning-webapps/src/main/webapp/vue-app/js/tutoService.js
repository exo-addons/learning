import { eLearningConstans } from '../js/elearningConstants.js';

export function getTutorialById(tutorialId) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/getTutorialById/${tutorialId}`, {
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

export function deleteTutorial(tutorialId) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/deleteTutorial/${tutorialId}`, {
    method: 'DELETE',
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}

export function addTutorial(tutorial) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/addTutorial`, {
    method: 'POST',
    body: JSON.stringify(tutorial),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    } else {
      return response.json();
    }
  });
}

export function updateTutorial(tutorial) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/updateTutorial`, {
    method: 'PUT',
    body: JSON.stringify(tutorial),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    } else {
      return response.json();
    }
  });
}

export function getTutorialsByTheme(themeId, offset, limit) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/getTutorialsByTheme/${themeId}?offset=${offset}&limit=${limit}`, {
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

export function getTutorialsByName(id, tutorialTitle) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/getTutorialsByName/${id}/${tutorialTitle}`, {
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