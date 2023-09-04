from flask import Flask, request, render_template


app = Flask(__name__)


def pvo(param):
    return param


# True positive
@app.route('/param1')
def main_param():
    param = request.args.get("param") or ''
    # True positive
    return render_template('index.html', param=param)


# False negative
@app.route('/param2')
def false_param():
    # If the analyzer requires compiled code, delete this fragment
    ''' #region

    argument = "harmless value"

    UnknownType.Property1 = parm1
    UnknownType.Property2 = UnknownType.Property1
    UnknownType.Property3 = cond1

    if UnknownType.Property3 == None:
        argument = UnknownType.Property2

    # An analyzer that ignores noncompiled code will report a vulnerability here
    # False positive
    return render_template('index.html', param=param)

    #endregion
    '''


if __name__ == '__main__':
    app.run(port=8080)


