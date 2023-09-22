import numpy as np
import random

def zero_padding(in_array, padding_size=0):
    rows, cols = in_array.shape
    padding_array = np.zeros([rows + 2 * padding_size, cols + 2 * padding_size])
    padding_array[padding_size:rows + padding_size, padding_size:cols + padding_size] = in_array
    return padding_array


def triplet_generator(classArrayDatas, batch_size, reshuffle_each_iteration=True):

    # batch counter
    counts = 0

    # 批Triplet组
    triplet_datas = []

    # count how much data is in each class
    class_len = []
    for i in range(len(classArrayDatas)):
        class_len.append(len(classArrayDatas[i]))


    class_index = []
    for i in range(len(classArrayDatas)):
        class_index.append(-1)

    curindex = 0

    while True:
        classnum = random.randint(0, len(classArrayDatas)-1)  # pick a category at random

        # confirm current index
        class_index[classnum] += 1
        curindex = class_index[classnum]

        # when current condition is not satisfied, traversing to find the replaceable category
        flag = 0
        switch_num = 0
        while class_index[classnum] >= class_len[classnum] - 1:
            flag = 1
            switch_num += 1
            classnum = (classnum + 1) % len(classArrayDatas)
            if switch_num == len(classArrayDatas):
                break

        # if can change, update the category
        if flag == 1 and switch_num != len(classArrayDatas):
            class_index[classnum] += 1
            curindex = class_index[classnum]

        # shuffle the data after each epoch, flag_2 was used to judge whether have experienced an epoch
        flag_2 = 0
        for k in range(len(classArrayDatas)):
            if class_index[k] < class_len[k]:
                flag_2 = 1
                break

        # shuffle data
        if flag_2 == 0 or switch_num == len(classArrayDatas):
            for i in range(len(classArrayDatas)):
                class_index[i] = -1
            if reshuffle_each_iteration:
                for i in range(len(classArrayDatas)):
                    np.random.shuffle(classArrayDatas[i])
            continue

        anchor = classArrayDatas[classnum][curindex]

        # choose positive
        positive_1 = classArrayDatas[classnum][random.randrange(0, len(classArrayDatas[classnum]))]

        # choose negative
        dn1 = random.randint(0, len(classArrayDatas) - 1)  #
        while dn1 == classnum:
            dn1 = random.randint(0, len(classArrayDatas) - 1)
        negative_1 = classArrayDatas[dn1][random.randrange(0, len(classArrayDatas[dn1]))]

        triplet_datas += [[anchor, positive_1, negative_1]]
        counts += 1
        if counts == batch_size:
            triplet_datas = np.array(triplet_datas)
            labels = np.empty((len(triplet_datas),))
            yield triplet_datas, labels
            triplet_datas = []
            counts = 0
