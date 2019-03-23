## Deploy DB without python
1. Create `sinners` DB and tables

    Run 'sinners.sql' in HeidiSQL SQL or Management Studio

    Run 'sinners-backup-v1.sql'


## Deploy DB with Python

1. Python 3.7.2
    - [download](https://www.python.org/downloads/release/python-372/)

    - Don't forget to choose "add Python to environment variables" when installing.

    - Alternatively add both `pythonXX` and `pythonXX\scripts` to `PATH` variable.

    - It is highly recommended to install for all users and use short installation path like `C:\python`.

    - ***Python may not work properly when path contains spaces***

2. [pip for python](https://packaging.python.org/tutorials/installing-packages/#ensure-you-can-run-pip-from-the-command-line)

    - To check installation:
        ```
        pip -V
        ```

    - If you get **`pip is an unknown command`**, make sure you have `pythonXX/scripts` in your paths or run:
        ```
        python -m ensurepip --default-pip
        ```

      Alternatively, download [get-pip](https://bootstrap.pypa.io/get-pip.py)
      installation file and run it from it's location directory:
        ```
        python get-pip.py
        ```

    - Upgrade `pip` and friends:
        ```
        python -m pip install --upgrade pip setuptools wheel
        ```
        If this fails with a PermissionError, then retry with administrator rights.

3. Install virtualenv
    ```
    --pip install virtualenv
    ```

4. Create and activate env in src/main/python
    - Create env in python folder
        ```
        python -m venv env
        ```
    
    -Activate it
        ```
        cd src\main\python
        env\Scripts\activate.bat
        ```


4. Install required libs that are included in `pips.txt`
    ```
    pip install -r pips.txt
    ```

    If you get an error about Microsoft Visual C++ is required, see point 6 below.

    If you get an error about mysql.h missing, then go to 

    https://www.lfd.uci.edu/~gohlke/pythonlibs/#mysql-python

    download the appropriate 

        mysqlclient‑1.4.2‑cp35‑cp35m‑win_amd64.whl
        mysqlclient‑1.4.2‑cp36‑cp36m‑win_amd64.whl
        mysqlclient‑1.4.2‑cp37‑cp37m‑win_amd64.whl

    and install with

    ```
    pip install mysqlclient‑1.4.2‑cp3X‑cp3Xm‑win_amd64.whl

5. Create `sinners` DB and tables

    Run 'sinners.sql' in HeidiSQL SQL or Management Studio

6. Parse bible and fill DB tables

    Run parsers
        ```
        python parse_bible.py
        python parse_sins.py
        ```
