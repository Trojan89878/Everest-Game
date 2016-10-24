#    ______     __   __   ______     ______     ______     ______     ______      ______     ______     __    __     ______
#   /\  ___\   /\ \ / /  /\  ___\   /\  == \   /\  ___\   /\  ___\   /\__  _\    /\  ___\   /\  __ \   /\ "-./  \   /\  ___\
#   \ \  __\   \ \ \'/   \ \  __\   \ \  __<   \ \  __\   \ \___  \  \/_/\ \/    \ \ \__ \  \ \  __ \  \ \ \-./\ \  \ \  __\
#    \ \_____\  \ \__|    \ \_____\  \ \_\ \_\  \ \_____\  \/\_____\    \ \_\     \ \_____\  \ \_\ \_\  \ \_\ \ \_\  \ \_____\
#     \/_____/   \/_/      \/_____/   \/_/ /_/   \/_____/   \/_____/     \/_/      \/_____/   \/_/\/_/   \/_/  \/_/   \/_____/
#                                                       By Troy Neubauer


import pygame
import time
import random
import math
import sys

pygame.init()
pygame.mixer.init()

debug = True
debug_lines = False
debug_event = False
debug_loco = False
debug_FPS = False
jumping = False

playing = True

display_width = 1600
display_height = 896

climber_slope_change = 0
x_change = 0
climber_O = 80
climber_landing_x = 0
climber_landing_y = 0
climber_width = 23
climber_height = 65
climber_x = 1570
climber_y = 619
climber_vel_y = 0
climber_jumping = False

CLIMBER = pygame.image.load('res/textures/climberstanding.png')
EVEREST_BACKGROUND = pygame.image.load('res/textures/Everest.png')
EVEREST_LOGO = pygame.image.load('res/textures/Everestlogo.png')
pygame.mixer.music.load("res/sounds/Pixel Peeker Polka - faster (1).mp3")
pygame.display.set_caption("Everest Game 1.0 Alpha")

pygame.display.set_icon(EVEREST_LOGO)

gameDisplay = pygame.display.set_mode((display_width,display_height))

version = "1.0"

clock = pygame.time.Clock()

lines = (((1597, 619),(1564, 618)),((1564, 618),(1551, 592)),((1551, 592),(1423, 536)),((1423, 536),(1411, 512)),
         ((1411, 512),(1256, 410)), ((1256,410),(1035,212)),((1035,212),(940,88)),((940,88),(887,82)),
         ((887,82),(853,62)),((853,62),(625,12)))

black = (0,0,0)
white = (255,255,255)
green = (0,225,0)
red = (225,0,0)
light_green = (0,255,0)
light_red = (255,0,0)


def text_objects(text, font):
    textSurface = font.render(text, True, black)
    return textSurface, textSurface.get_rect()

def reset():
    playing = True
    climber_x = 1570
    climber_y = 619
    gameLoop()

def messageDisplay(text, size, x, y):
    largeText = pygame.font.Font('freesansbold.ttf', size)
    TextSurf, TextRect = text_objects(text, largeText)
    TextRect.center = (x, y)
    gameDisplay.blit(TextSurf, TextRect)
    pygame.display.update()


def quitgame():
    pygame.mixer.music.stop()
    pygame.quit()
    quit()


def reduceO(climber_O):
    while True:
        climber_O -= 1
        time.sleep(1)

def button(msg,x,y,w,h,nc,bc, action, hovertext):

    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()

    if (x+w > mouse[0] > x and y+h > mouse[1] > y):
        pygame.draw.rect(gameDisplay, bc, (x,y,w,h))

        smallText = pygame.font.Font('freesansbold.ttf', 30)
        TextSurf, TextRect = text_objects(hovertext, smallText)
        TextRect.center = (mouse[0], mouse[1] + 20)
        gameDisplay.blit(TextSurf, TextRect)

        if click[0] == 1 and action != None:
            action()
    else:
        pygame.draw.rect(gameDisplay, nc, (x,y,w,h))
    smallText = pygame.font.Font('freesansbold.ttf', 40)
    TextSurf, TextRect = text_objects(msg, smallText)
    TextRect.center = (x+(w/2), y+(h/2))
    gameDisplay.blit(TextSurf, TextRect)



def findY(cx):
    cx = round(cx)
    x = 0
    if cx >= 1597:
        return 619, 1
    elif cx <= 625:
        return 12, 1
    if cx == 1564 or cx == 1563:
        return 618, 1
    if cx == 1551:
        return 592, 1
    if cx == 1423:
        return 536, 1
    if cx == 1411:
        return 512, 1
    if cx == 1256:
        return 410, 1
    if cx == 1035:
        return 212, 1
    if cx == 940:
        return 88, 1
    if cx == 887:
        return 82, 1
    if cx == 853:
        return 62, 1
    for x in range(0, len(lines)):
        x1 = lines[x][0][0]
        y1 = lines[x][0][1]
        x2 = lines[x][1][0]
        y2 = lines[x][1][1]

        if x1 < cx and x2 > cx or x1 > cx and x2 < cx:
            slope = 0
            b = 0
            slope = abs((y1 - y2) / (x1 - x2))
            b = slope * x2 * -1 + y2
            y = abs(slope * cx + b)
            return round(y), slope


def intro():

    pygame.mixer.music.play(-1)

    intro = True
    while intro:

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                playing = False
                quitgame()

        gameDisplay.fill(white)
        largeText = pygame.font.Font('freesansbold.ttf', 125)
        TextSurf, TextRect = text_objects("Everest Game", largeText)
        TextRect.center = ((display_width/2), (display_height/2))
        gameDisplay.blit(TextSurf, TextRect)

        largeText = pygame.font.Font('freesansbold.ttf', 40)
        TextSurf1, TextRect1 = text_objects("By Troy Neubauer Version: " + version + " Alpha for an English project", largeText)
        TextRect1.center = ((display_width/2), (display_height/1.3))
        gameDisplay.blit(TextSurf1, TextRect1)

        largeText = pygame.font.Font('freesansbold.ttf', 30)
        TextSurf1, TextRect1 = text_objects("Music by Kevin MacLeod", largeText)
        TextRect1.center = ((display_width/2), (display_height/1.215))
        gameDisplay.blit(TextSurf1, TextRect1)


        button("Play!", (display_width / 2 ) - 400, (display_height / 2) + 75, 300, 125, green, light_green, gameLoop, "Play the game!")
        button("Quit",  (display_width / 2 ) + 100, (display_height / 2) + 75, 300, 125, red, light_red, quitgame, "Aww don't leave!")

        pygame.display.update()
        clock.tick(120)


def gameLoop():

    ms = 0
    Time = 0
    Ptime = 0
    x_change = 0
    climber_O = 80
    climber_slope_change = 0
    climber_x = 1570
    climber_y = 619
    climber_landing_x = 0
    climber_landing_y = 0
    climber_slope = 1
    jumping = False
    playing = True
    reduceO(climber_O)
    while playing:
        if debug_FPS or debug:
            Time = pygame.time.get_ticks()
            ms = Time - Ptime
            FPS = 1/(ms/1000)
            messageDisplay("FPS: " + str(int(FPS)), 27,display_width - 75, 65)
        gameDisplay.fill([255,255,255])
        gameDisplay.blit(EVEREST_BACKGROUND,(0, 0))
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT and climber_x > 625 and not jumping:
                    x_change = -2

                if event.key == pygame.K_RIGHT and climber_x < 1597 and not jumping:
                    x_change = 2

                elif event.type == pygame.K_RIGHT and pygame.K_RIGHT:
                    x_change = 0
            if event.type == pygame.KEYUP:
                if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                    x_change = 0

            if debug_event:
                print(event)
            if event.type == pygame.QUIT:
                playing = False
                quitgame()
            if event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE:
                reset()
                intro()

        if climber_x < 1597 and x_change < 0 and not jumping:
            climber_slope_change = 4.5 - climber_slope * 2
            climber_x -= climber_slope_change

        elif climber_x > 625 and x_change > 0 and not jumping:
            climber_slope_change = 5 - climber_slope * 2
            climber_x += climber_slope_change


        if climber_x <= 625:
            intro()
        if climber_x >= 1571:
            climber_x = 1570


        if debug_loco or debug:
            xtext = "X: " + str(round(climber_x))
            ytext = "Y: " + str(climber_y)
            messageDisplay(xtext, 27, display_width - 50, 15)
            messageDisplay(ytext, 27, display_width - 50, 40)

        if debug_lines or debug:
            for x in range(0, len(lines)):
                x1 = lines[x][0][0]
                y1 = lines[x][0][1]
                x2 = lines[x][1][0]
                y2 = lines[x][1][1]
                pygame.draw.line(gameDisplay, black, (x1, y1), (x2, y2), 1)


        climber_y, climber_slope = findY(climber_x)

        messageDisplay("Oxygen: " + str(float(climber_O / 80 * 100)) + " %", 30, display_width - 250, 19)
        messageDisplay("Speed: " + str(round(climber_slope_change * 100) / 100)+ " px//s", 30, display_width - 250, 50)
        if climber_x != 1564:
            gameDisplay.blit(CLIMBER,(climber_x, (climber_y - climber_height)))

        if debug_FPS or debug:
            Ptime = pygame.time.get_ticks()

        pygame.display.update()
        clock.tick(60)


intro()
