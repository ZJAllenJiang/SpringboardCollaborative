import tensorflow as tf
import tensorflow.keras.backend as K
import pickle
import numpy as np
import pandas as pd
import json


class Responder:

    def __init__(self):
        self.baseEncoder = tf.keras.models.load_model('application/models/1')

        #the sequence according to label from low to high
        self.classArrayForTrain = np.load('application/resource/classArrayForTrain.npy', allow_pickle=True)

        self.trainCentorVectors = self.getTrainCentorVectors(self.classArrayForTrain, self.baseEncoder)
        self.scaler = pickle.load(open('application/resource/scaler.pkl', 'rb'))
        # self.navigatorList = self.loadNavigatorList()
        self.navigatorDict = self.loadNavigatorDict()
        print()


    # def loadNavigatorList(self):
    #     tables = pd.read_csv("application/resource/navigator.csv")
    #     datas = np.squeeze(tables.to_numpy())
    #     print(datas)
    #     return datas

    def loadNavigatorDict(self):

        tf = open("application/resource/navigatorDict.json", "r")
        navigatorDict = json.load(tf)
        tf.close()
        print(navigatorDict)
        return navigatorDict

    def getTrainCentorVectors(self, classArrayForTrain, baseEncoder):
        trainDatasResCentorVectors = []
        for classData in classArrayForTrain:
            res = baseEncoder(np.array(classData))
            totalNum = len(res)
            res = np.sum(res, axis=0) / totalNum
            trainDatasResCentorVectors.append(res)
        return trainDatasResCentorVectors

    def predict(self, data):

        data = np.array([data])
        data = self.scaler.transform(data)
        res = self.baseEncoder(data)

        distList = []
        for i in range(len(self.trainCentorVectors)):
            distList.append([K.sum(K.square(res[0] - self.trainCentorVectors[i]), axis=0), i])

        distList.sort(key=lambda x: (x[0]))
        resList = []

        navigatorDict = {v: k for k, v in self.navigatorDict.items()}

        for item in distList:
            resList.append(navigatorDict[item[1]])

        return resList










