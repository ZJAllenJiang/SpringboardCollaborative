from django.shortcuts import render, HttpResponse
from application.calculate.predict import Responder
import json
# Create your views here.
Responder = Responder()

# def Index(resquest):
#     return render(resquest,"index.html")

def getPrediction(request):

    if request.method == 'POST':
        data=json.loads(request.body)

        inputList = []
        inputList.append(data['sex'])
        inputList.append(data['urgent'])
        inputList.append(data['employment'])
        inputList.append(data['mentalHealthSupport'])
        inputList.append(data['medicalCare'])
        inputList.append(data['lifeSkills'])
        inputList.append(data['elderly'])
        inputList.append(data['serviceConnections'])
        inputList.append(data['reentry'])
        inputList.append(data['disability'])
        inputList.append(data['socialSecurity'])
        inputList.append(data['housingProgram'])

        for i in range(len(inputList)):
            if inputList[i] == "MALE":
                inputList[i] = 1
            if inputList[i] == "FEMALE":
                inputList[i] = 0
            if inputList[i] == True:
                inputList[i] = 1
            if inputList[i] == False:
                inputList[i] = 0

        res = Responder.predict(inputList)

        return HttpResponse(json.dumps(res, ensure_ascii=False), content_type="application/json; charset=utf-8")
