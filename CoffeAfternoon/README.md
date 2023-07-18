{
	"info": {
		"_postman_id": "c9bc82c4-25b8-434e-aea1-65c7c1cd3776",
		"name": "CoffeAfternoon",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "21675108"
	},
	"item": [
		{
			"name": "UserService",
			"item": [
				{
					"name": "Sing Up",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"name\":\"ADMIN\",\n    \"user\":\"admin\",\n    \"pass\":\"Thjnhotwp1@\",\n    \"type\":0\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/user/signup",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"user",
								"signup"
							]
						}
					},
					"response": []
				},
				{
					"name": "Sing In",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"user\":\"admin\",\n    \"pass\":\"Thjnhotwp1@\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/user/signin",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"user",
								"signin"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get All User",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/user/get-all",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"user",
								"get-all"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "CustomerService",
			"item": [
				{
					"name": "Get All Customer",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/customer/get-all",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"customer",
								"get-all"
							]
						}
					},
					"response": []
				},
				{
					"name": "Add Customer",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"phoneNumber\":\"0923001270\",\n    \"customerName\":\"Lê Duy Thịnh\",\n    \"type\":0\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/customer/add",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"customer",
								"add"
							]
						}
					},
					"response": []
				},
				{
					"name": "Find Customer By Name",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"phoneNumber\":\"0923001270\",\n    \"customerName\":\"duy\",\n    \"type\":0\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/customer/find-by-name",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"customer",
								"find-by-name"
							]
						}
					},
					"response": []
				},
				{
					"name": "Delete Customer",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"phoneNumber\":\"0923001270\",\n    \"customerName\":\"Lê Duy Thịnh\",\n    \"type\":0\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/customer/delete",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"customer",
								"delete"
							]
						}
					},
					"response": []
				},
				{
					"name": "Update Customer",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"phoneNumber\":\"0923001270\",\n    \"customerName\":\"Lê Duy Thịnh\",\n    \"type\":0\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/customer/update",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"customer",
								"update"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}