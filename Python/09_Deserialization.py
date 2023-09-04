from flask import Flask, request, render_template
import base64
import pickle
import json


# Class for serialization
class Pickle(object):
    def __reduce__(self):
        return eval, ("print('Danger!')",)


app = Flask(__name__)


@app.route('/param1')
def main_param():
    # Loading serialized data to insecure `pickle` library
    # True positive Deserialization attack
    param = request.args.get("param") or ''
    try:
        des_data = pickle.loads(base64.urlsafe_b64decode(param))
    except:
        pass

    # True positive XSS
    return render_template('index.html', param=param)


@app.route('/param2')
def main_param2():
    param = request.args.get("param") or ''

    # False positive Deserialization Attack
    # `json` library is safe
    js_data = json.loads(param)

    return 'Hello'


if __name__ == '__main__':
    # Print attack vector
    o = Pickle()
    p = pickle.dumps(o)
    print('Expoit: ', base64.urlsafe_b64encode(p))

    app.run(port=8080)


