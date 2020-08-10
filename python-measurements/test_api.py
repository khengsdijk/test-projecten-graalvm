import requests
import time
import entitydata
import random
import json


class ApiCalls:

    LOCAL_URL = 'http://localhost:'

    def __init__(self, project_port, entity):
        self.entity = entity
        self.project_port = project_port
        self.headers = {'Content-type': 'application/json', 'Accept': 'application/json'}

    # entity is one of the constants
    def get_entity(self, id):
        response = requests.get(self.LOCAL_URL + self.project_port + self.entity + "/" + str(id))
        return response.json()


    def get_entities(self):
        response = requests.get(self.LOCAL_URL + self.project_port + self.entity)
        return response.json()


    def delete_entity(self, id):
        response = requests.delete(self.LOCAL_URL + self.project_port + self.entity + "/" + str(id))
        


    def create_entity(self, entity_data):
        response = requests.post(self.LOCAL_URL + self.project_port + self.entity, data=json.dumps(entity_data), headers=self.headers)
        if response.status_code != 200:
            print(response)
            print(response.text)
            print(entity_data)
            return None
        return response.content


    def update_entity(self, entity_data, id):
        response = requests.put(self.LOCAL_URL + self.project_port + self.entity + "/" + str(id), data=json.dumps(entity_data), headers=self.headers)


#maybe get the project as a commandline argument 

class PerformTests():

    SUPPLIER = '/supplier'
    INGREDIENT = '/ingredient'
    PRODUCT = '/product'
    STORE = '/store' 
    
    # create the api call objects for all classes 
    def __init__(self, project_port):
        self.project_port = project_port
        self.supplier_api = ApiCalls(project_port, self.SUPPLIER)
        self.ingredient_api = ApiCalls(project_port, self.INGREDIENT)
        self.product_api = ApiCalls(project_port, self.PRODUCT)
        self.store_api = ApiCalls(project_port, self.STORE)

        self.suppliers = []
        self.ingredients = []
        self.products = []
        self.stores = []

    
    def add_suppliers(self):
        
        supplier = {
            "name" : random.choice(entitydata.WORDS),
            "country" : random.choice(entitydata.COUNTRIES),
            "type" : random.choice(entitydata.WORDS)
        }

        result = self.supplier_api.create_entity(supplier)
        if(result != None):
            self.suppliers.append(result)           

    
    def add_ingredients(self):
        ingredient = {
            "name": random.choice(entitydata.WORDS),
            "calories": random.randint(1, 1000),
            "supplier": json.loads(random.choice(self.suppliers))
        }
        
        result = self.ingredient_api.create_entity(ingredient)
        if(result != None):
            self.ingredients.append(result)  
        

    def add_products(self):
        product = {
            "name": random.choice(entitydata.WORDS),
            "ingredients": [json.loads(random.choice(self.ingredients))]
        }

        result = self.product_api.create_entity(product)
        if(result != None):
            self.products.append(result)  

    def add_store(self):
        store = {
            "name": random.choice(entitydata.WORDS),
            "country": random.choice(entitydata.COUNTRIES),
            "products": [json.loads(random.choice(self.products))]
        }

        result = self.store_api.create_entity(store)
        if(result != None):
            self.stores.append(result)    


    def add_entities(self):
        time.sleep(2)
        self.add_suppliers()
        time.sleep(2)
        self.add_ingredients()
        time.sleep(2)
        self.add_products()
        time.sleep(2)
        self.add_store()

    def retrieve_entities(self):
        time.sleep(2)
        self.supplier_api.get_entities()
        time.sleep(2)
        self.ingredient_api.get_entities()
        time.sleep(2)
        self.product_api.get_entities()
        time.sleep(2)
        self.store_api.get_entities()


    def delete_entities(self):
        time.sleep(2)
        entity_id = json.loads(random.choice(self.suppliers))
        self.supplier_api.delete_entity(entity_id['id'] )
        time.sleep(2)
        entity_id = json.loads(random.choice(self.ingredients))
        self.ingredient_api.delete_entity(entity_id['id'])
        time.sleep(2)
        entity_id = json.loads(random.choice(self.products))
        self.product_api.delete_entity(entity_id['id'])
        time.sleep(2)
        entity_id = json.loads(random.choice(self.stores))
        self.store_api.delete_entity(entity_id['id'])


    def update_entities(self):
        time.sleep(2)
        entity_id = json.loads(random.choice(self.suppliers))
        self.supplier_api.update_entity(json.loads(random.choice(self.suppliers)), entity_id['id'] )
        time.sleep(2)
        entity_id = json.loads(random.choice(self.ingredients))
        self.ingredient_api.update_entity(json.loads(random.choice(self.ingredients)), entity_id['id'])
        time.sleep(2)
        entity_id = json.loads(random.choice(self.products))
        self.product_api.update_entity(json.loads(random.choice(self.ingredients)), entity_id['id'])
        time.sleep(2)
        entity_id = json.loads(random.choice(self.stores))
        self.store_api.update_entity(json.loads(random.choice(self.ingredients)), entity_id['id'])


    def run_test_requests(self):
        
        # define percentage of which function is used more
        # this is done so that no empty requests will be made
        create = 0.40
        get = 0.20
        delete = 0.20
        update = 0.20
        
        number_of_calls = random.randint(20, 100)
        
        for x in range( int(number_of_calls * create)):
            self.add_entities()
        for x in range( int(number_of_calls * get)):
            self.retrieve_entities()
        for x in range( int(number_of_calls * get)):
            self.delete_entities()            
        for x in range( int(number_of_calls * get)):
            self.update_entities() 