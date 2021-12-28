scoreboard objectives add totem dummy
scoreboard objectives add isStoneling dummy
execute as @a store result score @s totem run clear @s consularsorigins:golemtotem 0
#The line below this comment will appear as though it has an error. But trust me, it works. It's only an error because "origin" isn't a vanilla command
execute as @a store result score @s isStoneling run origin has @s origins:origin consularsorigins:stoneling
advancement grant @a[scores={totem=1..,isStoneling=1}] only consularsorigins:used_golem_totem
clear @a[scores={totem=1..,isStoneling=1}] consularsorigins:golemtotem 1