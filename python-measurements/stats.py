import docker
import pprint
import numpy as np
import time
import csv
from os import path
import os

# this class measures the average memory use of a container
class MemoryMeasurement:

    def __init__(self, container, measure_time):
        self.container = container
        self.measurements = []
        self.measure_time = measure_time

    def get_current_memory(self):
        stats = self.container.stats(stream=False)
        memory_usage = stats["memory_stats"]["usage"]
        
        # convert bytes to megabytes
        result = memory_usage / 1024 / 1024
        self.measurements.append(result)
        time.sleep(1)


    '''
    Write the results to a file
    :param double result
    :param str filename
    '''
    def write_results_to_file(self, result):
        
        if not os.path.exists('results'):
            os.mkdir('results')

        with open('results/' + self.container.name + '.csv', 'w', newline='') as csvfile:
            writer = csv.writer(csvfile, delimiter=',')
            writer.writerow(["average_ram", "ram_per_second", "second"])
            writer.writerow([result, " ", " "])
            print( len(self.measurements))
            i = 1
            for x in self.measurements:
                writer.writerow([" ", x, i])
                i = i + 1


    def measure_average_memory_usage(self):
        while(self.measure_time > time.time()):
            self.get_current_memory()
        
        results = np.array(self.measurements)
        self.write_results_to_file(np.average(results))
        
