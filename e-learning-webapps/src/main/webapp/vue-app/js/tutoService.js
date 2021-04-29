export function getTutos() {
  return fetch('/portal/rest/tuto/getAllTutos', {
    method: 'GET'
  })
    .then((response) => response.json());
}

export function getTuto(tutoId) {
  return fetch(`/portal/rest/tuto/getTutoById/${tutoId}`, {
    method: 'GET'
  })
    .then((response) => response.json());
}

export function deleteTuto(deleteId) {
  return fetch(`/portal/rest/tuto/deleteTuto/${deleteId}`, {
    method: 'DELETE'
  });
}

export function tutoPost(tuto) {
  return fetch('/portal/rest/tuto/addTuto', {
    method: 'POST',
    body: JSON.stringify(tuto),
    headers: {
      'Content-Type': 'application/json'
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
  }
  );
}