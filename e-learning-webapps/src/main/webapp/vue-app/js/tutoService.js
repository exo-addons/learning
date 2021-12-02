import { eLearningConstans } from '../js/elearningConstants.js';

// Tutorial services

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

export function getTutorialsByTheme(themeId, spaceName, query, offset, limit) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/getTutorialsByTheme/${themeId}?spaceName=${spaceName}&q=${query}&offset=${offset}&limit=${limit}`, {
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

// Step services

export function getTutorialSteps(tutorialId) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/getTutorialSteps/${tutorialId}`, {
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

export function getTutorialStepByOrder(tutorialId, stepOrder) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/getTutorialStepByOrder/${tutorialId}/${stepOrder}`, {
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

export function updateStep(step) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/updateTutorialStep`, {
    method: 'PUT',
    body: JSON.stringify(step),
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

export function saveStep(step, tutorialId) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/addTutorialStep/${tutorialId}`, {
    method: 'POST',
    body: JSON.stringify(step),
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

export function deleteStep(deleteId) {
  return fetch(`${eLearningConstans.PORTAL}/${eLearningConstans.PORTAL_REST}/tutorial/deleteTutorialStep/${deleteId}`, {
    method: 'DELETE',
    credentials: 'include',
  }).then(response => {
    if (!response || !response.ok) {
      throw new Error('Response code indicates a server error', response);
    }
  });
}
