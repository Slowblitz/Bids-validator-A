#!/usr/bin/env python

import os
import errno
import json
import sys

def path_hierarchy(path):
    hierarchy = {
        'type': 'directory',
        'name': os.path.basename(path),
        'path': path,
    }

    try:
        hierarchy['children'] = [
            path_hierarchy(os.path.join(path, contents))
            for contents in os.listdir(path)
        ]
    except OSError as e:
        if e.errno != errno.ENOTDIR:
            raise
        hierarchy['type'] = 'file'

    return hierarchy


def get_name_in_dir(list_dict, names):

    for my_dict in list_dict:

        if my_dict['type'] == 'directory':
            names.append(my_dict['name'] )


            #print (my_dict)

            names = get_name_in_dir(my_dict['children'], names)

    return names

def verify_name(names):


    if






if __name__ == '__main__':
    import json
    import sys

    try:
        directory = sys.argv[1]

    except IndexError:
        directory = "."

    print(json.dumps(path_hierarchy(directory), indent=2, sort_keys=True))


    dic_data = json.loads(json.dumps(path_hierarchy(directory), indent=2, sort_keys=True))

    names = []
    names = get_name_in_dir([dic_data], names)
    arr=[]
    print ("list of folder : ")
    print( u" ".join(names))
