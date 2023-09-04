from flask import Flask, request, render_template


app = Flask(__name__)


# True positive
@app.route('/param1')
def main_param():
    if 1700 + 25 == 1725:
        param = request.args.get("param") or ''
        # True positive
        return render_template('index.html', param=param)


# False negative
@app.route('/param2')
def false_param():
    param = request.args.get("param") or ''
    summ = 0
    for i in range(10):
        for j in range(15):
            summ += i + j

    if summ != 1725:
        # An analyzer that approximates or ignores the interpretation of program loops will report a vulnerability here
        # False positive
        return render_template('index.html', param=param)
    return 'Hello'


if __name__ == '__main__':
    app.run(port=8080)

