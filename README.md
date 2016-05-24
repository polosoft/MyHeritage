# MyHeritage

I used ORMLite with SQLite Database,

the db schema:
Users table - with user details (first name, last name etc...)
RelationShipUsers table - a many-to-many table:
primary user, secondary user and relationship type.
The relationship type can be:
1 - partners
2 - parent
3 - sibling
that means that if the type = 1 then the "primary user" is the parent of the secondary user... and then we dont have duplicates.

I implemented AsyncTaskLoader in order to perform DB operations in a background thread. when loader is finished the ui should be updated.

Each table has interface with CRUD operations (api) - IUserRepo and IRelationshipRepo.
This interface is implemented in the main repo - MyHeritageRepo.

This is not a full implementation but you can see the project architecture and how I implemented it.

Thanks.
Noa.
