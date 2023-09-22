import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler,MinMaxScaler
import pickle
import json

def dataStandardize(datas):
    scaler = MinMaxScaler()
    datas = scaler.fit_transform(datas)
    return datas

#use test data
def dataLoad():
    #load table
    tables=pd.read_csv("train.csv")
    print(tables.head())

    #change csv table to numpy matrix, and deal with separately
    datas=tables.to_numpy()
    labels=datas[:,0]
    datas=datas[:,1:]

    #data standardize
    # datas = dataStandardize(datas)

    #get userDatas and targetDatas
    return datas, labels

# use real data
def dataLoad_v2():
    #load table
    tables=pd.read_csv("train.csv")
    print(tables.head())

    #change csv table to numpy matrix, and deal with separately
    datas=tables.to_numpy()


    navigatorList=datas[:,0]
    datas=datas[:,1:]

    #generate labels

    navigatorSet = set([])
    for navigator in navigatorList:
        navigatorSet.add(navigator)
    navigatorDict = {}

    index=0
    for navigator in navigatorSet:
        navigatorDict[navigator]=index
        index+=1

    labels=[]
    for navigator in navigatorList:
        labels.append(navigatorDict[navigator])

    tf = open('resource/navigatorDict.json', "w")
    json.dump(navigatorDict, tf)
    tf.close()

    labels = np.array(labels)

    return datas, labels

#set trainDatas and testData according to labels
def dataSplit(datas,labels,persentage):

    #random the index
    index = np.arange(datas.shape[0])
    np.random.shuffle(index)

    datas = datas[index]
    labels = labels[index]

    labelSet = set([])
    for label in labels:
        labelSet.add(label)

    # split for train and test
    trainDatas = datas[:(int)(datas.shape[0] * persentage)]
    trainLabels = labels[:(int)(labels.shape[0] * persentage)]

    testDatas = datas[(int)(datas.shape[0] * persentage):]
    testLabels = labels[(int)(labels.shape[0] * persentage):]

    # data standardize
    scaler = MinMaxScaler()
    trainDatas = scaler.fit_transform(trainDatas)
    testDatas = scaler.transform(testDatas)
    pickle.dump(scaler, open('resource/scaler.pkl','wb'))


    classArrayForTrain = []
    classArrayForTest = []

    #init class array
    for i in range(len(labelSet)):
        classArrayForTrain.append([])
        classArrayForTest.append([])

    #add data order by index
    for i in range(trainDatas.shape[0]):
        classArrayForTrain[trainLabels[i]].append(trainDatas[i])
    for i in range(testDatas.shape[0]):
        classArrayForTest[testLabels[i]].append(testDatas[i])

    return np.array(classArrayForTrain), np.array(classArrayForTest), trainDatas.shape[0], testDatas.shape[0]

def getDatas(persentage):
    # datas,labels = dataLoad()
    datas, labels = dataLoad_v2()
    classArrayForTrain, classArrayForTest, trianDatasNum, testDatasNum = dataSplit(datas,labels,persentage)
    return classArrayForTrain, classArrayForTest, trianDatasNum, testDatasNum

