import docker
import time
from stats import MemoryMeasurement
import threading
import concurrent.futures
import requests
import test_api


def measure_memory(used_container, time):
    memory = MemoryMeasurement(used_container, time)
    memory.measure_average_memory_usage()


def make_request(runtime, project):
    api_calls = test_api.PerformTests(project)
    while(runtime > time.time()):
        api_calls.run_test_requests()


# run a image of a project as a docker container
def run_image(project_name, image_name, port):
    client = docker.from_env()
    container = client.containers.run(project_name + image_name, detach=True, name=image_name, ports={port : port})
    # let the container start up before testing
    time.sleep(5)

    # the measured time
    runtime = time.time() + 60 * 1

    memoryThread = threading.Thread(target=measure_memory, args=(container, runtime))
    requestThread = threading.Thread(target=make_request, args=(runtime, port))

    memoryThread.start()
    requestThread.start()

    requestThread.join()
    memoryThread.join()

    container.kill()
    container.remove(force=True)


'''
Run multiple docker images of a project
param: str project the project to use or the docker tag
param: list[str] images a list with the name of the docker images
param: str port the port used for the project 
'''
def run_project(project, images, port):
    for image in images:
        run_image(project, image, port)


SPRING_PORT = '8081'
QUARKUS_PORT = '8082'
MICRONAUT_PORT = '8083'

quarkus_tag_name = 'quarkus/'
quarkus_images = [
    'foodtracker-quarkus-jvm',
    'foodtracker-quarkus-community',
    'foodtracker-quarkus-enterprise',
]


micronaut_tag_name = 'micronaut/'
micronaut_images = [
    'foodtracker-micronaut-jvm',
    'foodtracker-micronaut-community',
    'foodtracker-micronaut-enterprise',
]

spring_tag_name = 'spring/'
spring_images = [
    'foodtracker-spring-jvm',
    'foodtracker-spring-community'
]


run_project(quarkus_tag_name, quarkus_images, QUARKUS_PORT)
run_project(micronaut_tag_name, micronaut_images, MICRONAUT_PORT)
run_project(spring_tag_name, spring_images, SPRING_PORT)
