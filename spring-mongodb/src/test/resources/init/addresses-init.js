db = db.getSiblingDB('test');
db.createCollection('addresses');
db.addresses.insertMany([{
    "pid": "26b919a4-a47a-4e0e-9327-4aad0ebaa03a",
    "aid": "580df118-86b9-496b-93f8-679289a709b4",
    "country": "Nigeria",
    "street": "06824 Southridge Way",
    "postal_code": null
}, {
    "pid": "61145683-b13f-4ef4-a4c3-e33a2b5a8a35",
    "aid": "cb911f7c-de03-414d-b1fc-5ee0162cb867",
    "country": "Brazil",
    "street": "136 Gateway Circle",
    "postal_code": "57230-000"
}, {
    "pid": "2035bf06-a79d-46ce-8f80-64295ac2f8ef",
    "aid": "77e6fd32-c25a-492b-8688-cceef02de2e1",
    "country": "France",
    "street": "862 Logan Hill",
    "postal_code": "48005 CEDEX"
}, {
    "pid": "61145683-b13f-4ef4-a4c3-e33a2b5a8a35",
    "aid": "5ca574ce-304b-4df9-a85e-8837280c0610",
    "country": "Brazil",
    "street": "5 5th Alley",
    "postal_code": null
}]);
