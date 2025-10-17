#!/bin/bash
set -e

echo "Updating MongoDB users"

mongosh --username user --password pass --authenticationDatabase admin <<EOF

use users-query;

db.dropUser("users-query");
db.dropUser("users-sync");

db.createUser({
    user: "users-sync",
    pwd: "users-sync"
    roles: [{
        role: "dbOwner", 
        db: "users-query"
    }]
})

db.createUser({
    user: "users-query",
    pwd: "users-query"
    roles: [{
        role: "read", 
        db: "users-query"
    }]
})

EOF

echo "MongoDB user update completed"