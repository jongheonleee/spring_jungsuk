<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        #top {
            margin-top: 5em;
            margin-bottom: 5em;
        }
        #player1 {
            float : left;
            width : 33%;
            margin-left: 3px;
            text-align: center;
        }
        #player2 {
            float : left;
            width : 33%;
            margin-right: 3px;
            text-align: center;
        }
        #title {
            float : left;
            width : 33%;
            text-align: center;
        }
        #middle {
            margin-top: 10em;
            margin-bottom: 10em;
            text-align : center;
        }
        #bottom {
            margin-top: 5em;
            margin-bottom: 5em;
            text-align: center;
        }
        #bottom {
            text-align: center;
        }
    </style>
</head>
<body>
    <!--1. 상단 -->
    <div id="top" style="color : red">
        <span id="player1" class="top">
        </span>

        <span id="title" class="top">
            <h1>Dice Game</h1>
        </span>

        <span id="player2" class="top">
        </span>
    </div>

    <br>

    <!--2. 중간 -->
    <div id="middle" style="color : blue">
        <!-- 사용자 이름 -->
        <h2 id="currentPlayer">player1</h2>
        <!-- 뽑은 다이스 보여줌, 최대 3개-->
        <div id="diceArea">

        </div>
    </div>
    <!--3. 하단 -->
    <div id="bottom" style="color : green">
        <button id="player1Btn">
            플레이어1 던지기 
        </button>
        <button id="resultBtn">
            결과 출력
        </button>
        <button id="player2Btn">
            플레이어2 던지기 
        </button>
    </div>
    <script type="text/javascript">
        // 객체 : Dice, Player, GameModerator

            // 1. Dice : 주사위 객체 ✅
                // 숫자 저장
                // 파일 경로 저장 및 반환
                // 자신의 점수 반환 

            // 2. Player : 플레이어 객체 ✅
                // 점수 및 남은 횟수 저장 
                // 최종 점수 반환 

            // 3. GameModerator : 게임 진행 객체 📌(설계도 다듬기)
                // 이벤트 처리
                    // 던지기 버튼
                        // 랜덤 숫자 생성
                        // 해당 숫자와 매칭되는 다이스 보여줌
                    
                    // 결과 출력 버튼 
                        // 플레이어의 점수 비교
                        // 게임 결과 판단 및 발표

                    // 그 외의 정상 흐름 유도 


        // 1. Dice : 주사위 객체
            // 숫자 저장
            // 파일 경로 저장 및 반환
            // 자신의 점수 반환 
        class Dice {
            
            #number; // number 타입, 1~6까지 허용 범위 
            #resource; // 이미지 파일 위치 

            constructor(number) {
                this.#number = this.isValidNumber(number) ? number : -1;
                this.#resource = `img/dice_img/dice${this.#number}.jpg`;
            }

            isValidNumber(number) {
                return typeof number === 'number' && 1 <= number && number <= 6;
            }

            get number() {
                return this.#number;
            }

            get resouce() {
                return this.#resource;
            }
        }

        // 2. Player : 플레이어 객체
            // 점수 및 남은 횟수 저장 
            // 최종 점수 반환 

        class Player {

            #name; // string 타입, 0보다 길고 20보다 적어야함
            #score; // number 타입, 기본 0 으로 세팅
            #leftCount; // number 타입, 기본 3으로 세팅

            constructor(name) {
                this.#name = this.isValidName(name) ? name : "player";
                this.#score = 0;
                this.#leftCount = 3;
            }

            isValidName(name) {
                return typeof name === 'string' && 0 < name.length && name.length < 20; 
            }

            get name() {
                return this.#name;
            }

            get score() {
                return this.#score;
            }

            get leftCount() {
                return this.#leftCount;
            }

            addScore(amount) {
                if (amount < 0) return;
                this.#score += amount;
            }

            isLeftCount() {
                return this.#leftCount > 0;
            }

            decreaseLeftCount() {
                if (this.#leftCount > 0 ) {
                    this.#leftCount--;
                }
            }
        }


        // 3. GameModerator : 게임 진행 객체
            // 이벤트 처리
                // 던지기 버튼
                    // 랜덤 숫자 생성
                    // 해당 숫자와 매칭되는 다이스 보여줌
                    
                // 결과 출력 버튼 
                    // 플레이어의 점수 비교
                    // 게임 결과 판단 및 발표

                // 그 외의 정상 흐름 유도 

        class GameModerator {
            // 다이스 주머니
            // 플레이어 객체 2
            // 난수 생성기 
            #dices;
            #players;
            #currentPlayer;
            #randomGenerator;

            constructor() {
                this.createDices();
                this.createPlayers();
                this.createRandomGenerator();
            }

            // 플레이어 객체 생성 및 추가
            // 랜덤 픽
            // 보여줌
            // 게임 결과 발표
            // 게임 현황 업데이트

            createDices() {
                if (typeof this.#dices !== "undefined") return;

                this.#dices = [];
                for (let i=1; i<=6; i++) {
                    this.#dices.push(new Dice(i));
                }
            }

            createPlayers() {
                if (typeof this.#players !== "undefined") return;
                this.#players = [];

                if (typeof this.#currentPlayer !== "undefined") return;
                this.#currentPlayer = null;
                
            }

            createRandomGenerator() {
                if (typeof this.#randomGenerator !== "undefined") return;
                this.#randomGenerator = () => Math.floor(1 + Math.random() * 5);
            }

            addPlayer(name) {
                if (this.#players.length == 2) {
                    alert("모든 플레이어가 생성되었습니다.");
                    return;
                }

                this.#players.push(new Player(name));
            }

            throwDice() {
                // 남은 횟수가 없는 경우 
                if (!this.#currentPlayer.isLeftCount()) {
                    alert(`${this.#currentPlayer.name}는 남은 횟수가 없습니다.`);
                    return;
                }

                // 다이스 가져옴
                const randomNumber = this.#randomGenerator();
                const randomIdx = randomNumber - 1;
                const selectedDice = findSelectedDice(randomIdx);

                // 현재 플레이어에 남은 횟수 감소 및 점수 추가
                this.#currentPlayer.decreaseLeftCount();
                this.#currentPlayer.addScore(diec.number);

                // 화면 업데이트 
                updateDisplay(selectedDice);
            }

            findSelectedDice(idx) {
                return this.#dices[idx];
            }
            

            updateDisplay(selectedDice) {
                // 현재 플레이어 기록 업데이트
                this.showPlayersRecord();
                // 다이스 화면 업데이트 
                this.showCurrentPlayerDiceRecord(selectedDice);
            }

            showPlayersRecord() {
                // 참조
                // html 업데이트
                const firstPlayerArea = document.getElementById("player1");
                const secondPlayerArea = document.getElementById("player2");

                firstPlayerArea.innerHTML = `<h2>${this.#players[0].name}</h2> <p>남은 횟수 : ${this.#players[0].leftCount}, 현재 점수 : ${this.#players[0].score}<p>`;
                secondPlayerArea.innerHTML = `<h2>${this.#players[1].name}</h2> <p>남은 횟수 : ${this.#players[1].leftCount}, 현재 점수 : ${this.#players[1].score}<p>`;
            }

            showCurrentPlayerDiceRecord(selectedDice) {
                // 참조
                // html 업데이트
                const diceArea = document.getElementById("diceArea");
                diceArea.innerHTML += `<img src = ${selectedDice.resouce}>`;
            }

            notifyGameWinner() {

            }

        }

        window.onload = function() {
            const gm = new GameModerator();

            gm.addPlayer("yenouel");
            gm.addPlayer("hanuel");

            gm.updateDisplay(new Dice(6));
            gm.updateDisplay(new Dice(4));
            gm.updateDisplay(new Dice(3));
        }
            
    </script>
</body>
</html>