import React, { useEffect, useState } from "react";

import { Button, FormControl, InputGroup } from "react-bootstrap";
import { Modal } from "react-bootstrap";
import { Container } from "react-bootstrap";
import todosService from "../services/todos.service";

export default function NewTodo() {
  const [show, setShow] = useState(false);

  const [title, setTitle] = useState("");
  const [text, setText] = useState("");

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleSave = () => {
    todosService.createTodo(title, text).then((it) => handleClose());
  };

  const saveTodo = () => {};

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Launch demo modal
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create new todo</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <InputGroup className="mb-3">
            <FormControl
              placeholder="Name of todo"
              aria-label="todo-name"
              aria-describedby="basic-addon1"
              value={title}
              onChange={(event) => setTitle(event.target.value)}
            />
          </InputGroup>
          <InputGroup>
            <FormControl
              placeholder="Your todo description"
              as="textarea"
              aria-label="With textarea"
              value={text}
              onChange={(event) => setText(event.target.value)}
            />
          </InputGroup>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSave}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
