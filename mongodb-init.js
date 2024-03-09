print("Started Adding the Users.");
db = db.getSiblingDB("websocketscomponents");
db.createUser({
  user: "userx",
  pwd: "1234",
  roles: [{ role: "readWrite", db: "websocketscomponents" }],
});
print("End Adding the User Roles.");