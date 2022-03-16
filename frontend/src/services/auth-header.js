export default function authHeader() {
  const user = JSON.parse(localStorage.getItem('user'));
  console.log("hello");
  console.log(user);
  if (user && user.token) {
    return { Authorization: 'Bearer ' + user.token }; // for Spring Boot back-end
  } else {
    return {};
  }
}
